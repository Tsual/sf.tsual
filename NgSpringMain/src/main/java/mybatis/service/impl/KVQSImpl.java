package mybatis.service.impl;

import mybatis.entity.KV;
import mybatis.mapper.KVMapper;
import mybatis.service.interfaces.IKVQS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KVQSImpl implements IKVQS {
    private final KVMapper kvMapper;

    @Autowired
    public KVQSImpl(KVMapper kvMapper) {
        this.kvMapper = kvMapper;
    }

    @Override
    public List<KV> getKVs() {
        return kvMapper.getKVs();
    }
}
