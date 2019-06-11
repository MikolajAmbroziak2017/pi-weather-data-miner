package pl.ambroziak.sensorapp.Services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pl.ambroziak.sensorapp.Model.SeriesData;

@Service
public class RabbitServices {
    @Autowired
   private RabbitTemplate rabbitTemplate;
    @GetMapping("/send")
    public void sentMessage(SeriesData seriesDataTem,SeriesData seriesDataHum) {
        rabbitTemplate.convertAndSend("temperature", seriesDataTem);
        rabbitTemplate.convertAndSend("huminidty", seriesDataHum);
    System.out.println(seriesDataTem.getX()+seriesDataTem.getY());
        System.out.println(seriesDataHum.getX()+seriesDataHum.getY());

    }
}

