package drugBankTD;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

public class AvroConvertor {


    public List<byte[]> launchProducer(ArrayList<Map<String, String>> records) throws IOException {
        //File file = new File(getClass().getClassLoader().getResource("/home/pierrejean/IdeaProjects/dataEngineeringTP1/schema.avsc").getFile());
        var file = new String(Files.readAllBytes(Paths.get("/Users/louis_billaut/Desktop/M2/data_engineer/project2/TP2_Data_Engineering/schema.avsc")));
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(file);
        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
        ArrayList lst = new ArrayList<byte[]>();
        for (int i = 0; i < records.size(); i++){
            GenericData.Record avroRecord = new GenericData.Record(schema);
            avroRecord.put("id", records.get(i).get("id"));
            avroRecord.put("fname", records.get(i).get("fname"));
            avroRecord.put("lname", records.get(i).get("lname"));
            avroRecord.put("vaccine", records.get(i).get("vaccine"));
            avroRecord.put("date", records.get(i).get("date"));
            avroRecord.put("siderCode", records.get(i).get("siderCode"));
            lst.add(recordInjection.apply(avroRecord));
            //lst.add(avroRecord);
        }
        return lst;
    }

    public List<byte[]> launchAgeProducer(ArrayList<Map<String, String>> records) throws IOException {
        //File file = new File(getClass().getClassLoader().getResource("/home/pierrejean/IdeaProjects/dataEngineeringTP1/schema.avsc").getFile());
        var file = new String(Files.readAllBytes(Paths.get("/Users/louis_billaut/Desktop/M2/data_engineer/project2/TP2_Data_Engineering/schemaAge.avsc")));
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(file);
        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
        ArrayList lst = new ArrayList<byte[]>();
        for (int i = 0; i < records.size(); i++){
            GenericData.Record avroRecord = new GenericData.Record(schema);
            avroRecord.put("id", records.get(i).get("id"));
            avroRecord.put("age", records.get(i).get("age"));
            lst.add(recordInjection.apply(avroRecord));
        }
        return lst;
    }

    public List<byte[]> launchAnonymousProducer(ArrayList<Map<String, String>> records) throws IOException {
        //File file = new File(getClass().getClassLoader().getResource("/home/pierrejean/IdeaProjects/dataEngineeringTP1/schema.avsc").getFile());
        var file = new String(Files.readAllBytes(Paths.get("/Users/louis_billaut/Desktop/M2/data_engineer/project2/TP2_Data_Engineering/schemaAnonymous.avsc")));
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(file);
        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
        ArrayList lst = new ArrayList<byte[]>();
        for (int i = 0; i < records.size(); i++){
            GenericData.Record avroRecord = new GenericData.Record(schema);
            avroRecord.put("id", records.get(i).get("id"));
            avroRecord.put("vaccine", records.get(i).get("vaccine"));
            avroRecord.put("date", records.get(i).get("date"));
            avroRecord.put("siderCode", records.get(i).get("siderCode"));
            lst.add(recordInjection.apply(avroRecord));
        }
        return lst;
    }


}
