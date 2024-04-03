package com.managermoneyapi.services.impl;

import com.managermoneyapi.dto.TransferRequestDto;
import com.managermoneyapi.dto.TransferResponseDto;
import com.managermoneyapi.entity.Account;
import com.managermoneyapi.entity.AccountBalance;
import com.managermoneyapi.entity.Transfer;
import com.managermoneyapi.mappers.TransferResponseMapper;
import com.managermoneyapi.repositories.AccountBalanceRepository;
import com.managermoneyapi.repositories.AccountRepository;
import com.managermoneyapi.repositories.TransferRepository;
import com.managermoneyapi.services.TransferService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Override
    public List<TransferResponseDto> findAll() {
        return transferRepository.findAll()
                .stream()
                .map(TransferResponseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TransferResponseDto> findById(Long id) {
        return transferRepository.findById(id)
                .map(TransferResponseMapper::toDTO);
    }

    @Override
    @Transactional
    public TransferResponseDto save(TransferRequestDto transferRequestDto) {

        Optional<Account> accountOrigin = accountRepository.findById(transferRequestDto.getAccountOrigin());
        Optional<Account> accountDestination = accountRepository.findById(transferRequestDto.getAccountDestination());

        if (accountOrigin.isPresent() && accountDestination.isPresent()){

            Transfer transfer = Transfer
                    .builder()
                    .accountOrigin(accountOrigin.get())
                    .accountDestination(accountDestination.get())
                    .amount(transferRequestDto.getAmount())
                    .date_time(LocalDate.now().toString())
                    .build();

            Transfer transferSaved  = transferRepository.save(transfer);

            AccountBalance accountBalanceOrigin = accountOrigin.get().getAccountBalance();

            accountBalanceOrigin.setBalance(accountBalanceOrigin.getBalance().subtract(transferRequestDto.getAmount()));

            accountBalanceRepository.save(accountBalanceOrigin);

            AccountBalance accountBalanceDestination = accountDestination.get().getAccountBalance();

            accountBalanceDestination.setBalance(accountBalanceDestination.getBalance().add(transferRequestDto.getAmount()));

            accountBalanceRepository.save(accountBalanceDestination);

            return TransferResponseMapper.toDTO(transferSaved);
        }

        return null;
    };

    @Override
    public Optional<TransferResponseDto> update(Long id, TransferRequestDto transferRequestDto) {

        Optional<Transfer> transferOptional = transferRepository.findById(id);

        if (transferOptional.isPresent()){

            Transfer transferToUpdate = transferOptional.get();

            Optional<AccountBalance> accountBalanceOrigin = accountBalanceRepository.findByAccountId(transferToUpdate.getAccountOrigin().getId());

            Optional<AccountBalance> accountBalanceDestination = accountBalanceRepository.findByAccountId(transferToUpdate.getAccountDestination().getId());

            if(accountBalanceOrigin.isPresent() && accountBalanceDestination.isPresent()){

                BigDecimal amountDifference = transferToUpdate.getAmount().subtract(transferRequestDto.getAmount());

                if (amountDifference.compareTo(BigDecimal.ZERO) > 0) {

                    accountBalanceOrigin.get().setBalance(accountBalanceOrigin.get().getBalance().add(amountDifference));
                    accountBalanceRepository.save(accountBalanceOrigin.get());

                    accountBalanceDestination.get().setBalance(accountBalanceDestination.get().getBalance().subtract(amountDifference));
                    accountBalanceRepository.save(accountBalanceDestination.get());

                    transferToUpdate.setAmount(transferRequestDto.getAmount());

                    return Optional.of(TransferResponseMapper.toDTO(transferRepository.save(transferToUpdate)));
                }
            }

        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

        Optional<Transfer> transfer = transferRepository.findById(id);

        if (transfer.isPresent()){

            AccountBalance accountBalanceOrigin = accountBalanceRepository.findByAccountId(transfer.get().getAccountOrigin().getId()).get();
            AccountBalance accountBalanceDestination = accountBalanceRepository.findByAccountId(transfer.get().getAccountDestination().getId()).get();

            accountBalanceOrigin.setBalance(accountBalanceOrigin.getBalance().add(transfer.get().getAmount()));
            accountBalanceRepository.save(accountBalanceOrigin);

            accountBalanceDestination.setBalance(accountBalanceDestination.getBalance().subtract(transfer.get().getAmount()));
            accountBalanceRepository.save(accountBalanceDestination);

            transferRepository.delete(transfer.get());
        }
    }
}
