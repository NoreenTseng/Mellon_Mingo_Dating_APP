package com.sda.datingapp.model.state;

import com.sda.datingapp.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.sda.datingapp.util.Constants.*;

@Component
public class StoryStateContext {
    @Autowired
    private DraftState draftState;

    @Autowired
    private PublishedState publishedState;

    @Autowired
    private ArchivedState archivedState;

    private StoryState storyState;

    public void setCurrState(String currStateString) {
        StoryState state = null;
        switch(currStateString) {
            case DRAFT_STATE:
                state = draftState;
                break;
            case PUBLISHED_STATE:
                state = publishedState;
                break;
            case ARCHIVED_STATE:
                state = archivedState;
                break;
        }
        if (state != null) {
            storyState = state;
        } else {
            // Handle the case when an invalid state string is provided
            System.out.println("Invalid state: " + currStateString);
        }
    }

    public void changeState(String newState, Integer storyId) {
        switch(newState) {
            case DRAFT_STATE:
                storyState.changeToDraft(storyId);
                break;
            case PUBLISHED_STATE:
                storyState.changeToPublished(storyId);
                break;
            case ARCHIVED_STATE:
                storyState.changeToArchived(storyId);
                break;
        }
    }
    public StoryState getStoryState() {
        return this.storyState;
    }
}
