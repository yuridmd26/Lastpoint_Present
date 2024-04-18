package br.com.present.attendances.consumer;

import br.com.present.attendances.consumer.model.UserResponseDTO;
import br.com.present.commons.util.consts.MicroservicesConsts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(MicroservicesConsts.User.NAME)
public interface IUserConsumer {

    @GetMapping("/v1/user/{id}")
    UserResponseDTO findById(@PathVariable Long id);

}