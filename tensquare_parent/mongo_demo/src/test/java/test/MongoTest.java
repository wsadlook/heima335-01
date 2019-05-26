package test;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MongoTest {
    @Test
    public void test1(){
        //0.创建客户端
        MongoClient mongoClient = new MongoClient("192.168.200.128");

        //1.选择数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");

        //2.获取集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");

        //3.使用集合查询所有数据
        FindIterable<Document> documents = spit.find();

        //4.展示结果
        for (Document document : documents) {
            System.out.println("__________________________");
            System.out.println(document.get("_id"));
            System.out.println(document.getString("content"));
            System.out.println(document.getString("userid"));
            System.out.println(document.getString("nickname"));
            System.out.println(document.getInteger("visits"));
        }

    }
    @Test
    public void test2(){
        //0.创建客户端
        MongoClient mongoClient = new MongoClient("192.168.200.128");

        //1.选择数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");

        //2.获取集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");

        //3.根据条件查询数据
        BasicDBObject basicDBObject = new BasicDBObject("userid","1013");
        FindIterable<Document> documents = spit.find(basicDBObject);

        //4.展示结果
        for (Document document : documents) {
            System.out.println(document.getString("_id"));
            System.out.println(document.getString("content"));
            System.out.println(document.getString("userid"));
            System.out.println(document.getString("nickname"));
            System.out.println(document.getInteger("visits"));
        }

    }
    @Test
    public void test3(){
        //0.创建客户端
        MongoClient mongoClient = new MongoClient("192.168.200.128");

        //1.获取数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");

        //2.获取集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");

        //3.往集合中插入一条数据
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("_id","123");
        map.put("content","高薪就业，赢取白富门，走上人生巅峰");
        map.put("userid","111");
        map.put("nickname","yangyangyang");
        map.put("visits",8888);
        Document document = new Document(map);
        spit.insertOne(document);
    }
}
