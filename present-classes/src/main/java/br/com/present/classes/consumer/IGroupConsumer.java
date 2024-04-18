package br.com.present.classes.consumer;

import br.com.present.classes.consumer.model.GroupResponseDTO;
import br.com.present.commons.util.consts.MicroservicesConsts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(MicroservicesConsts.Group.NAME)
public interface IGroupConsumer {

    @GetMapping("/v1/group/{id}")
    GroupResponseDTO findById(@PathVariable Long id);

}