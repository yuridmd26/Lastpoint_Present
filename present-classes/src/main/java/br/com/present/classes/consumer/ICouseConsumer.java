package br.com.present.classes.consumer;

import br.com.present.classes.consumer.model.CourseResponseDTO;
import br.com.present.commons.util.consts.MicroservicesConsts;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(MicroservicesConsts.Course.NAME)
public interface ICouseConsumer {

    @GetMapping("/v1/course")
    CourseResponseDTO findById(@PathVariable Long id);

}