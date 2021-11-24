package rob.instagramapprealdemo.TabLayoutPackage;

public class struct {

    private String username;
    private String postMessage;
    private int imagePosts;

    public struct(String username, String postMessage, int imagePosts) {
        this.username = username;
        this.postMessage = postMessage;
        this.imagePosts = imagePosts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public int getImagePosts() {
        return imagePosts;
    }

    public void setImagePosts(int imagePosts) {
        this.imagePosts = imagePosts;
    }
}
