package iti.android.foodplanner.data.authentication;

public class AuthenticationFactory {
    public final static int FACEBOOK = 1;
    public final static int EMAIL = 2;
    public final static int TWITTER = 3;
    public final static int GOOGLE = 4;

    private AuthenticationFactory() {
    }

    public static Authentication authenticationManager(int authType){
        Authentication authentication = null;
        switch (authType){
            case FACEBOOK:
                authentication = new FacebookAuth();
                break;
            case TWITTER:
                authentication = new TwitterAuth();
                break;
            case GOOGLE:
                authentication = new GoogleAuth();
                break;
            case EMAIL:
                authentication = new EmailAndPasswordAuth();
                break;

            default:
                authentication = new GoogleAuth();
        }


        return authentication;
    }
}
