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
    public void sentMessage(SeriesData seriesDataTem, SeriesData seriesDataHum) {
        System.out.println("Convert to send . . .");
        rabbitTemplate.convertAndSend("weather","weather.temperature", seriesDataTem);
        rabbitTemplate.convertAndSend("weather","weather.humidity", seriesDataHum);
        System.out.println("Sent data: ");
        System.out.println(seriesDataTem.getValue() + seriesDataTem.getGainDate());
        System.out.println(seriesDataHum.getValue() + seriesDataHum.getGainDate());


    }
}

