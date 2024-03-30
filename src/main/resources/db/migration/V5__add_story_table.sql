-- Create Stories Table
CREATE TABLE stories (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id int(11) NOT NULL,
                         text_content TEXT,
                         img_content TEXT, -- Assuming JSON string will be stored in a TEXT field
                         visibility VARCHAR(10), -- 'Private', 'Public', 'Friends'
                         timestamp TIMESTAMP,
                         FOREIGN KEY (user_id) REFERENCES Users(id) -- Assuming you have a Users table
);

-- Create Likes Table
CREATE TABLE likes (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       story_id INT,
                       user_id int(11) NOT NULL,
                       timestamp TIMESTAMP,
                       FOREIGN KEY (story_id) REFERENCES Stories(id),
                       FOREIGN KEY (user_id) REFERENCES Users(id) -- Assuming you have a Users table
);
