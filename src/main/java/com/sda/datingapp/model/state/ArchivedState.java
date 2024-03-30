package com.sda.datingapp.model.state;

import com.sda.datingapp.model.Story;
import com.sda.datingapp.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sda.datingapp.util.Constants.DRAFT_STATE;

@Service
public class ArchivedState implements StoryState {

    @Autowired
    StoryRepository storyRepository;

    @Override
    public void changeToDraft(Integer storyId) {
        //do nothing
    }

    @Override
    public void changeToPublished(Integer storyId) {
        //do nothing
    }

    @Override
    public void changeToArchived(Integer storyId) {
        //do nothing
    }

    @Override
    public boolean canEdit() {
        return false;
    }

    @Override
    public boolean visibleInStoryLine() {
        return false;
    }
}
