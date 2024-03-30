package com.sda.datingapp.service;

import com.sda.datingapp.model.Match;
import com.sda.datingapp.vo.MatchedProfileVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MatchService {

    public List<MatchedProfileVO> getMatches(Integer userId);

}
