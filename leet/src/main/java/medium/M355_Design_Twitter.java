package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author gaiqun
 * @date 2020/4/13
 *
 * 总结
 *
 * 使用队列维护所有feed。每个用户维护follower列表。
 * 从队列中获取最新的feed，并且这些feed所属的用户属于follower
 *
 * 注意每个操作都可能先进行，所以如果User不存在，需要先创建User
 */
public class M355_Design_Twitter {

    public static void main(String[] args) {
        M355_Design_Twitter solution = new M355_Design_Twitter();
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 4);
        twitter.postTweet(2, 5);
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    static class Twitter {

        private List<Feed> queue;
        private Map<Integer, User> users;

        /** Initialize your data structure here. */
        public Twitter() {
            queue = new ArrayList<>();
            users = new HashMap<>();
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            createNewUser(userId);
            queue.add(new Feed(userId, tweetId));
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            createNewUser(userId);
            Set<Integer> followers = users.get(userId).followers;
            int count = 10;
            int index = queue.size() - 1;
            List<Integer> newsFeeds = new ArrayList<>();
            while (index >= 0 && count != 0) {
                Feed feed = queue.get(index);
                if (followers.contains(feed.userId)) {
                    newsFeeds.add(feed.feedId);
                    count--;
                }
                index--;
            }
            return newsFeeds;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            createNewUser(followerId);
            createNewUser(followeeId);
            User user = users.get(followerId);
            user.followers.add(followeeId);
            users.put(followerId, user);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            createNewUser(followerId);
            createNewUser(followeeId);
            if (followerId != followeeId) {
                User user = users.get(followerId);
                user.followers.remove(followeeId);
                users.put(followerId, user);
            }
        }

        private void createNewUser(int userId) {
            if (!users.containsKey(userId)) {
                users.put(userId, new User(userId));
            }
        }

        static class User {

            private Set<Integer> followers;

            User(int userId) {
                followers = new HashSet<>();
                followers.add(userId);
            }
        }

        static class Feed {

            private int userId;
            private int feedId;

            Feed(int userId, int feedId) {
                this.userId = userId;
                this.feedId = feedId;
            }
        }
    }
}
