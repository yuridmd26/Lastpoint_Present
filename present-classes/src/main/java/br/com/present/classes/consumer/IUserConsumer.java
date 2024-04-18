package br.com.present.classes.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.present.classes.consumer.model.UserTypeResponseDTO;
import br.com.present.commons.util.consts.MicroservicesConsts;

@FeignClient(MicroservicesConsts.User.NAME)
public interface IUserConsumer{

    @GetMapping("/v1/user/{id}")
    UserTypeResponseDTO findTypeByUserId(@PathVariable Long id);
}