package restful.ServiceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restful.Reponsitories.ChannelRepository;
import restful.entities.Channel;
import restful.services.ChannelService;

/**
 *
 * developer VuChung
 */
@Service
@Transactional
public class ChannelServiceImp implements ChannelService {

    
    @Autowired
    private ChannelRepository channelRepository;
    
    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    public Channel findById(String id) {
        return channelRepository.findOneById(id);
    }

    @Override
    public void save(Channel channel) {
        channelRepository.save(channel);
    }

    @Override
    public List<Channel> findByAssignTo(String assignTo) {
        //System.out.println("ServiceImp"+assignTo);
        return channelRepository.findByAssignTo(assignTo);
    }

    @Override
    public Channel findByChannelId(String channelId) {
        return channelRepository.findByChannelId(channelId);
    }


}
