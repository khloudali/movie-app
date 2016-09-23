package myapplication90.khloud.example.com.myapplication_demo2;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class Favorite {
    Realm realm;
    RealmResults<Movi> realmResults;
    Context context;



    public Favorite(Context context) {
        realm = Realm.getInstance(context);
        this.context = context;
    }

    public void insert(Long id, String poster, String title) {
        realm.beginTransaction();
        Movi object = new Movi(id, poster, title);
        realm.copyToRealm(object);
        realm.commitTransaction();
    }

    public ArrayList<Movi> retrive() {
        if (realm == null) {
            realm = Realm.getInstance(context);
        }
        ArrayList<Movi> mydata = new ArrayList<>();

        realmResults = realm.where(Movi.class).findAll();
        for (int i = 0; i < realmResults.size(); i++) {
            mydata.add(realmResults.get(i));
        }
        return mydata;
    }

    public boolean retrivebyID(long id) {
        if (realm == null) {
            realm = Realm.getInstance(context);
        }

        realmResults = realm.where(Movi.class).equalTo("id", id).findAll();
        if (realmResults != null & !realmResults.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
