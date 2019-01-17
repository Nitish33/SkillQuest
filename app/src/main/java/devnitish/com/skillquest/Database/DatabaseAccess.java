package devnitish.com.skillquest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import devnitish.com.skillquest.Models.CategoryModel;
import devnitish.com.skillquest.Models.TrainerModel;
import devnitish.com.skillquest.Models.TrainingModel;

public class DatabaseAccess {


    public static ArrayList<CategoryModel> getAllCategory(Context context){

        ArrayList<CategoryModel> allCategory = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getDatabase();

        String query = "select cid,name,description,imagePath from category";
        Cursor cursor = database.rawQuery(query,null);

        allCategory.clear();

        while (cursor.moveToNext()){

            int cid = cursor.getInt(0);
            String name = cursor.getString(1).trim();
            String description =  cursor.getString(2).trim();
            String imagePath = cursor.getString(3);

            CategoryModel model = new CategoryModel(cid,name,description,imagePath);

            allCategory.add(model);

        }

        Log.e("all category ",allCategory.toString());

        return allCategory;
    }


    public static CategoryModel getCategoryDetail(Context context,int cid){

        CategoryModel model =null;

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getDatabase();

        String query = "select cid,name,description,imagePath from category" +
                " where cid = "+cid;
        Cursor cursor = database.rawQuery(query,null);


        while (cursor.moveToNext()){

            String name = cursor.getString(1).trim();
            String description =  cursor.getString(2).trim();
            String imagePath = cursor.getString(3);

             model = new CategoryModel(cid,name,description,imagePath);

        }

      return model;
    }


    public static ArrayList<TrainingModel> getAllTraining(Context context){

        ArrayList<TrainingModel> allTraining = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getDatabase();

        String query = "select tid,cid,trainerId,name,description,startDate," +
                "availibility,duration,cost,location,imagePath from training";

        Cursor cursor = database.rawQuery(query,null);

        allTraining.clear();
        while (cursor.moveToNext()){

            int tid = cursor.getInt(0);
            int cid = cursor.getInt(1);
            int trainerId = cursor.getInt(2);
            String name = cursor.getString(3).trim();
            String description = cursor.getString(4).trim();
            String startDate = cursor.getString(5).trim();
            String availibility = cursor.getString(6).trim();
            String duration = cursor.getString(7).trim();
            float cost = cursor.getFloat(8);
            String location = cursor.getString(9).trim();
            String imagePath = cursor.getString(10);

            TrainingModel model = new TrainingModel(tid,cid,trainerId,name,description,
                    startDate,availibility,duration,cost,location,imagePath);

            allTraining.add(model);
        }

        return allTraining;
    }

    public static ArrayList<TrainingModel> getCategoryTraining(
            Context context,int cid){

        ArrayList<TrainingModel> allTraining = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getDatabase();

        String query = "select tid,cid,trainerId,name,description,startDate," +
                "availibility,duration,cost,location," +
                "imagePath  from training where cid = "+cid;

        Cursor cursor = database.rawQuery(query,null);

        allTraining.clear();
        while (cursor.moveToNext()){

            int tid = cursor.getInt(0);
            int trainerId = cursor.getInt(2);
            String name = cursor.getString(3).trim();
            String description = cursor.getString(4).trim();
            String startDate = cursor.getString(5).trim();
            String availbility = cursor.getString(6).trim();
            String duration = cursor.getString(7).trim();
            float cost = cursor.getFloat(8);
            String location = cursor.getString(9).trim();
            String imagePath = cursor.getString(10);

            TrainingModel model = new TrainingModel(tid,cid,trainerId,name,description,
                    startDate,availbility,duration,cost,location,imagePath);

            allTraining.add(model);
        }

        return allTraining;
    }


    public static ArrayList<TrainingModel> getTeacherTrainings(
            Context context,int trainerId){

        ArrayList<TrainingModel> allTraining = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getDatabase();

        String query = "select tid,cid,trainerId,name,description,startDate," +
                "availibility,duration,cost,location," +
                "imagePath  from training where trainerId = "+trainerId;

        Cursor cursor = database.rawQuery(query,null);

        allTraining.clear();

        while (cursor.moveToNext()){

            int tid = cursor.getInt(0);
            int cid = cursor.getInt(1);
            String name = cursor.getString(3).trim();
            String description = cursor.getString(4).trim();
            String startDate = cursor.getString(5).trim();
            String availbility = cursor.getString(6).trim();
            String duration = cursor.getString(7).trim();
            float cost = cursor.getFloat(8);
            String location = cursor.getString(9).trim();
            String imagePath = cursor.getString(10);

            TrainingModel model = new TrainingModel(tid,cid,trainerId,name,description,
                    startDate,availbility,duration,cost,location,imagePath);

            allTraining.add(model);
        }

        return allTraining;
    }


    public static TrainingModel getTraining(Context context,int tid){

        TrainingModel model = null;

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getDatabase();

        String query = "select tid,cid,trainerId,name,description,startDate," +
                "availibility,duration,cost,location," +
                "imagePath  from training where tid = "+tid;

        Cursor cursor = database.rawQuery(query,null);


        if (cursor.moveToNext()){

            int cid = cursor.getInt(1);
            int trainerId = cursor.getInt(2);
            String name = cursor.getString(3).trim();
            String description = cursor.getString(4).trim();
            String startDate = cursor.getString(5).trim();
            String availibility = cursor.getString(6).trim();
            String duration = cursor.getString(7).trim();
            float cost = cursor.getFloat(8);
            String location = cursor.getString(9).trim();
            String imagePath = cursor.getString(10);

             model = new TrainingModel(tid,cid,trainerId,name,description,
                    startDate,availibility,duration,cost,location,imagePath);


        }

        return model;
    }

    public static TrainerModel
    getTrainerDetail(Context context,int trainerId){

        TrainerModel trainerModel = null;

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getDatabase();

        String query = "select trainerName,details,qualification,experiance," +
                "expertAt from trainer where trainerId = "+trainerId;

        Cursor cursor = database.rawQuery(query,null);

        if (cursor.moveToNext()){

            String tainerName = cursor.getString(0).trim();
            String details  = cursor.getString(1).trim();
            String qualification = cursor.getString(2).trim();
            String experiance = cursor.getString(3).trim();
            String expertAt = cursor.getString(4).trim();


            trainerModel = new TrainerModel(trainerId,tainerName,details,qualification,expertAt,experiance);
        }


        return trainerModel;
    }


    public static void registerUser(Context context,
                                    String userName,String password,
                                    String phone){

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("user",userName);
        cv.put("phone",phone);
        cv.put("password",password);

        database.insert("login",null,cv);
        database.close();
    }

    public static String checkCredentials(Context context,
                                           String phone,
                                           String password){

        DatabaseHelper helper =new DatabaseHelper(context);
        SQLiteDatabase database  = helper.getWritableDatabase();

        String query = "select user from login where phone = '"+phone+"' and password = '"+password+"'";

        Cursor c = database.rawQuery(query,null);

        if(c.moveToNext()){
            return c.getString(0);
        }
        else {
            return null;
        }
    }

}
