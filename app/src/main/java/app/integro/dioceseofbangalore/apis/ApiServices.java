package app.integro.dioceseofbangalore.apis;

import app.integro.dioceseofbangalore.models.ABEInstitutionDataList;
import app.integro.dioceseofbangalore.models.ADSList;
import app.integro.dioceseofbangalore.models.AboutUsList;
import app.integro.dioceseofbangalore.models.CircularList;
import app.integro.dioceseofbangalore.models.CuriaDataList;
import app.integro.dioceseofbangalore.models.CuriaList;
import app.integro.dioceseofbangalore.models.GalleryList;
import app.integro.dioceseofbangalore.models.InstitutionList;
import app.integro.dioceseofbangalore.models.NewsImagesList;
import app.integro.dioceseofbangalore.models.NewsList;
import app.integro.dioceseofbangalore.models.NotificationList;
import app.integro.dioceseofbangalore.models.OrganisationDataList;
import app.integro.dioceseofbangalore.models.OrganisationList;
import app.integro.dioceseofbangalore.models.PalanaBavanList;
import app.integro.dioceseofbangalore.models.PalanaBavanaList2;
import app.integro.dioceseofbangalore.models.ParishesList;
import app.integro.dioceseofbangalore.models.PrincipalMessageList;
import app.integro.dioceseofbangalore.models.PublicationList;
import app.integro.dioceseofbangalore.models.ReligiousHousesDataList;
import app.integro.dioceseofbangalore.models.ReligiousHousesList;
import app.integro.dioceseofbangalore.models.VideosList;
import app.integro.dioceseofbangalore.models.WordofgodList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("diocesan_news.php")
    Call<NewsList> getNewsList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("news_images.php")
    Call<NewsImagesList> getNewsImagesList(@Field("news_id") String news_id);

    @FormUrlEncoded
    @POST("diocesan_notification.php")
    Call<NotificationList> getNotificationsList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_about.php")
    Call<AboutUsList> getAboutUsList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_principal.php")
    Call<PrincipalMessageList> getMessageList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_circular.php")
    Call<CircularList> getCircularList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_engagement.php")
    Call<CircularList> getBishopsEngagementList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_gallery.php")
    Call<GalleryList> getGalleryList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_mass.php")
    Call<ParishesList> getMassList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_video.php")
    Call<VideosList> getVideosList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_org.php")
    Call<OrganisationList> getOrganisationList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_org1.php")
    Call<OrganisationDataList> getOrganisationDataList(@Field("o_id") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_quotes.php")
    Call<WordofgodList> getWordOfGod(@Field("date") String date);

    @FormUrlEncoded
    @POST("diocesan_publication.php")
    Call<PublicationList> getPublicationList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_inst1.php")
    Call<InstitutionList> getInstitutionList(@Field("tag") String tag);

    @FormUrlEncoded
    @POST("diocesan_inst2.php")
    Call<ABEInstitutionDataList> getInstitutionDataList(@Field("oid") String oid);

    @FormUrlEncoded
    @POST("diocesan_religious.php")
    Call<ReligiousHousesList> getReligiousHousesList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_religious2.php")
    Call<ReligiousHousesDataList> getReligiousHousesDataList(@Field("rid") String rid);

    @FormUrlEncoded
    @POST("diocesan_paalanaa.php")
    Call<PalanaBavanList> getPalanaBavanList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_paalanaa1.php")
    Call<PalanaBavanaList2> getPalanaBavanaList2(@Field("p_id") String p_id);

    @FormUrlEncoded
    @POST("diocesan_curia.php")
    Call<CuriaList> getCuriaList(@Field("updated_at") String updated_at);

    @FormUrlEncoded
    @POST("diocesan_curia1.php")
    Call<CuriaDataList> getCuriaDataList(@Field("c_id") String c_id);

    @GET("diocesan_ad.php")
    Call<ADSList> getADSList();
}
