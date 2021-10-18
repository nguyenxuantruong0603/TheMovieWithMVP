package com.example.demomvppattern.model.mymovie;

import com.example.demomvppattern.listener.IHomeContract;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MyMovieRepository implements IHomeContract.MyMovieRepo {
    private final IHomeContract.MyMovieListener myMovieListener;
    private final Realm realm;

    public MyMovieRepository(IHomeContract.MyMovieListener myMovieListener) {
        this.myMovieListener = myMovieListener;

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("MyRealmDB.realm")
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(configuration);
    }

    @Override
    public void addMyMovie(MyMovie myMovie) {
        realm.executeTransactionAsync(realm -> realm.insert(myMovie),
                () -> {
                    myMovieListener.addDataSuccess("Success add " + myMovie.getMovieName());
                    realm.close();
                }, error -> {
                    myMovieListener.addDataError("Fail add " + myMovie.getMovieName() + error.getMessage());
                    realm.close();
                });
    }

    @Override
    public void updateMyMovie(String movieName, String title, String desc, Boolean video) {
        realm.executeTransactionAsync(realm -> {
            MyMovie myMovie = realm.where(MyMovie.class).equalTo("movieName", movieName).findFirst();
            Objects.requireNonNull(myMovie).setMovieTitle(title);
            Objects.requireNonNull(myMovie).setMovieDescription(desc);
            Objects.requireNonNull(myMovie).setMovieVideo(video);
        }, () -> {
            myMovieListener.updateDataSuccess("Update thành công");
            realm.close();
        }, error -> {
            myMovieListener.updateDataError(error.getMessage());
            realm.close();
        });
    }

    @Override
    public void deleteMyMovie(String movieName) {
        realm.executeTransactionAsync(realm -> {
            MyMovie myMovie1 = realm.where(MyMovie.class).equalTo("movieName", movieName).findFirst();
            Objects.requireNonNull(myMovie1).deleteFromRealm();
        }, () -> {
            myMovieListener.deleteDataSuccess("Delete Success" + movieName);
            realm.close();
        }, error -> {
            myMovieListener.deleteDataError(error.getMessage());
            realm.close();
        });

    }

    @Override
    public void getListMyMovie() {
        RealmResults<MyMovie> movieRealmResults = realm.where(MyMovie.class).findAll();
        myMovieListener.getAllMovie(movieRealmResults);

//           filter a collection in DB
            /* RealmResults<Task> TasksThatBeginWithN = Tasks.where().beginsWith("name", "N").findAll();
            RealmResults<Task> openTasks = Tasks.where().equalTo("status", TaskStatus.Open.name()).findAll();*/
    }

}
