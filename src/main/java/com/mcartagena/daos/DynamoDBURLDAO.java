package com.mcartagena.daos;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.kms.model.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDBURLDAO implements UrlDAO{

  private static final String ID_TABLE_NAME = "maxid";
  private static final String URL_TABLE_NAME = "URL";

  @Autowired
  private AmazonDynamoDB amazonDynamoDB;
  
  @Override
	public String generateShortcode() {
    DynamoDB dyanomDB = new DynamoDB(amazonDynamoDB);
    Table table = dyanomDB.getTable(ID_TABLE_NAME);

    UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("id",1)
    .withUpdateExpression("set shortCode = shortCode + :val")
    .withValueMap(new ValueMap().withNumber(":val", 1)).withReturnValues(ReturnValue.UPDATED_OLD);

    UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

		return Long.toString(Long.parseLong(outcome.getItem().get("shortCode").toString()),36);
	}

	@Override
	public void storeURL(String shortCode, String url) {
    Map<String, AttributeValue> item = new HashMap<>();
    item.put("shortCode",new AttributeValue(shortCode));
    item.put("url",new AttributeValue(url));

    PutItemRequest request = new PutItemRequest(URL_TABLE_NAME, item);
    amazonDynamoDB.putItem(request);
		
	}

    @Override
    public String getUrl(String shortCode) {
      Map<String, AttributeValue> keyToGet = new HashMap();

      keyToGet.put("shortCode", new AttributeValue(shortCode));

      GetItemRequest request = new GetItemRequest()
        .withKey(keyToGet)
        .withTableName(URL_TABLE_NAME);

      GetItemResult result = amazonDynamoDB.getItem(request);

      if(result.getItem() == null){
        throw new NotFoundException("Couldn't find the shortcode mapping");
      }

      return result.getItem().get("url").getS();
      
    }
  
}
