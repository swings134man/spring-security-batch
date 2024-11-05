package com.lucas.bomkey.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientInfoRepository repository;

    @Transactional(readOnly = true)
    public List<ClientInfo> getClient() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<ClientInfo> getClient(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ClientInfo getClientById(String clientId) {
        return repository.findByClientId(clientId);
    }

    @Transactional
    public ClientInfo saveClient(ClientInfo info) {
        if(info.getClientId() == null) {
            return save(info);
        }else {
            return update(info);
        }
    }

    private ClientInfo save(ClientInfo info) {
        info.setClientId(UUID.randomUUID().toString());
        info.setClientSecret(RandomStringUtils.randomAlphanumeric(15));
        ClientInfo save = repository.save(info);
        return save;
    }

    private ClientInfo update(ClientInfo info) {
        ClientInfo res = getClientById(info.getClientId());
        res.setClientName(info.getClientName());
        res.setClientSecret(info.getClientSecret());
        res.setClientDesc(info.getClientDesc());
        res.setClientUrl(info.getClientUrl());
        res.setRedirectUrl(info.getRedirectUrl());

        ClientInfo save = repository.save(res);
        return save;
    }
}
