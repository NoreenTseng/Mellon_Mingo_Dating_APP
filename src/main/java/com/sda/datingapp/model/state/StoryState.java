package com.sda.datingapp.model.state;

import jakarta.persistence.Embeddable;

@Embeddable
public interface StoryState {
    void changeToDraft(Integer storyId);
    void changeToPublished(Integer storyId);
    void changeToArchived(Integer storyId);
    boolean canEdit();
    boolean visibleInStoryLine();
}
