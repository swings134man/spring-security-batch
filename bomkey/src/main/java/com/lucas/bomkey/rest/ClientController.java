package com.lucas.bomkey.rest;

import com.lucas.bomkey.keys.RsaKeys;
import com.lucas.bomkey.keys.RsaKeysService;
import com.lucas.bomkey.oauth_client.OAuthClient;
import com.lucas.bomkey.oauth_client.OAuthClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/client")
public class ClientController {

    private final OAuthClientService service;
    private final RsaKeysService rsaKeysService;

    @GetMapping("v1/page")
    public Page<OAuthClient> getPagedClients(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<OAuthClient> client = service.getClient(pageable);
        return new ResponseEntity<>(client, HttpStatus.OK).getBody();
    }

    @PostMapping("/v1")
    public ResponseEntity<OAuthClient> saveClient(@RequestBody OAuthClient clientInfo) {
        OAuthClient result = service.saveClient(clientInfo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable Long id) {
        boolean b = service.deleteClient(id);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    // Save RsaKeys
    @PostMapping("/v1/{identifier}/keys")
    public ResponseEntity<RsaKeys> saveRsaKey(@PathVariable("identifier") String identifier) {
        RsaKeys rsaKeys = rsaKeysService.saveRsaKeys(identifier);
        return new ResponseEntity<>(rsaKeys, HttpStatus.OK);
    }

}
