package com.dh.serieservice.Service.Impl;

import com.dh.serieservice.Model.Serie;
import com.dh.serieservice.Repository.SerieRepository;
import com.dh.serieservice.Service.ISerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService implements ISerieService {

    public static Logger LOG = LoggerFactory.getLogger(SerieService.class);

    @Value("${queue.serie.name}")
    private String serieQueue;

    private final SerieRepository serieRepository;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SerieService(SerieRepository serieRepository, RabbitTemplate rabbitTemplate) {
        this.serieRepository = serieRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Serie> getListByGenre(String genre) {
        List<Serie> series = serieRepository.findAllByGenre(genre);
        LOG.info("Busqueda: " + series);
        return series;
    }

    @Override
    public void save(Serie serie) {
        serieRepository.save(serie);
        LOG.info("Serie registrada: " + serie);
        rabbitTemplate.convertAndSend(serieQueue, serie);
        LOG.info("Serie en cola");
    }
}
