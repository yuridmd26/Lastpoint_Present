package br.com.present.attendances.consumer;

import br.com.present.attendances.consumer.model.EventResponseDTO;
import br.com.present.commons.util.consts.MicroservicesConsts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(MicroservicesConsts.Event.NAME)
public interface IEventConsumer {

    @GetMapping("/v1/event/{id}")
    EventResponseDTO findById(@PathVariable Long id);

}