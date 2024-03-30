package com.sda.datingapp.model.state;

import com.sda.datingapp.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sda.datingapp.util.Constants.*;

@Service
public class PublishedState implements StoryState {
    @Autowired
    StoryRepository storyRepository;

    @Override
    public void changeToDraft(Integer storyId) {
        storyRepository.updateState(storyId, DRAFT_STATE);
    }

    @Override
    public void changeToPublished(Integer storyId) {
        //do nothing
    }

    @Override
    public void changeToArchived(Integer storyId) {
        storyRepository.updateState(storyId, ARCHIVED_STATE);
    }

    @Override
    public boolean canEdit() {
        return false;
    }

    @Override
    public boolean visibleInStoryLine() {
        return true;
    }
}
