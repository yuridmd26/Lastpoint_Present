package br.com.present.classes.consumer;

import br.com.present.classes.consumer.model.DisciplineResponseDTO;
import br.com.present.commons.util.consts.MicroservicesConsts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(MicroservicesConsts.Discipline.NAME)
public interface IDisciplineConsumer {

    @GetMapping("/v1/discipline/{id}")
    DisciplineResponseDTO findById(@PathVariable Long id);

}