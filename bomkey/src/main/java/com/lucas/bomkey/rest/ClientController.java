package com.lucas.bomkey.rest;

import com.lucas.bomkey.client.ClientInfo;
import com.lucas.bomkey.client.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/v1")
    public ResponseEntity<List<ClientInfo>> findAllClient() {
        List<ClientInfo> client = clientService.getClient();
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("v1/page")
    public Page<ClientInfo> getPagedClients(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<ClientInfo> client = clientService.getClient(pageable);
        return new ResponseEntity<>(client, HttpStatus.OK).getBody();
    }

    @PostMapping("/v1")
    public ResponseEntity<ClientInfo> saveClient(@RequestBody ClientInfo clientInfo) {
        ClientInfo result = clientService.saveClient(clientInfo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
