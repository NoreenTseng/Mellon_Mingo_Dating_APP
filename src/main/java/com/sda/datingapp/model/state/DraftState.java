package com.sda.datingapp.model.state;

import com.sda.datingapp.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sda.datingapp.util.Constants.*;

@Service
public class DraftState implements StoryState {
    @Autowired
    StoryRepository storyRepository;

    @Override
    public void changeToDraft(Integer storyId) {
        //do nothing
    }

    @Override
    public void changeToPublished(Integer storyId) {
        storyRepository.updateState(storyId, PUBLISHED_STATE);
    }

    @Override
    public void changeToArchived(Integer storyId) {
        storyRepository.updateState(storyId, ARCHIVED_STATE);
    }

    @Override
    public boolean canEdit() {
        return true;
    }

    @Override
    public boolean visibleInStoryLine() {
        return false;
    }
}
