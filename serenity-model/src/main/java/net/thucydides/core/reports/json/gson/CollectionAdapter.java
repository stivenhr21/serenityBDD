package net.thucydides.core.reports.json.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Collection;

public class CollectionAdapter implements JsonSerializer<Collection<?>> {
  @Override
  public JsonElement serialize(Collection<?> src, Type typeOfSrc, JsonSerializationContext context) {
    if (src == null || src.isEmpty()) // exclusion is made here
      return null;

    JsonArray array = new JsonArray();

    for (Object child : src) {
      JsonElement element = context.serialize(child);
      array.add(element);
    }

    return array;
  }
}