package utils.cv;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 13/02/2015.
 */
public class OtherInformation {

    private String personalWebSite;
    private List<SocialNetworks> socialNetworksList;

    public OtherInformation(String personalWebSite){
        this.personalWebSite = personalWebSite;
        this.socialNetworksList = new ArrayList<SocialNetworks>();
    }

    public OtherInformation(String personalWebSite, List<SocialNetworks> socialNetworksList){
        this.personalWebSite = personalWebSite;
        this.socialNetworksList = socialNetworksList;
    }

    public void addSocialNetwork(String network, String user){
        this.socialNetworksList.add(new SocialNetworks(network, user));
    }

    public class SocialNetworks{
        private String network;
        private String user;

        public SocialNetworks(String network, String user){
            this.network = network;
            this.user = user;
        }

        public String getNetwork() {
            return network;
        }

        public String getUser() {
            return user;
        }


    }

    public String getPersonalWebSite() {
        return personalWebSite;
    }

    public List<SocialNetworks> getSocialNetworksList() {
        return socialNetworksList;
    }
}
