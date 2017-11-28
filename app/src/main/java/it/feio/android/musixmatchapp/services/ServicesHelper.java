package it.feio.android.musixmatchapp.services;

import it.feio.android.musixmatchapp.BuildConfig;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.math.BigDecimal;


public class ServicesHelper {

	private static final String TAG = ServicesHelper.class.getSimpleName();

	public static Flowable<LinkedTreeMap> getTracks() {
		return getServices().getTracksChart("it");
	}

	public static Flowable<LinkedTreeMap> getTrackLyric(String trackId) {
		return getServices().getTrackLyric(trackId);
	}

	private static ServicesInterface getServices() {
		return new Retrofit.Builder()
				.baseUrl(BuildConfig.API_BASE_URL)
				.addConverterFactory(getGsonConverterFactory())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
				.create(ServicesInterface.class);
	}

	private static GsonConverterFactory getGsonConverterFactory() {
		GsonBuilder asd = new GsonBuilder();
		asd.registerTypeAdapter(Double.class, new JsonDeserializer<Double>() {
			@Override
			public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return null;
			}
		})
				.registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {
					@Override
					public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
							throws JsonParseException {
						return null;
					}
				})
				.registerTypeAdapter(Float.class, new JsonDeserializer<Float>() {
					@Override
					public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
							throws JsonParseException {
						return null;
					}
				})
				.registerTypeHierarchyAdapter(Number.class, new JsonDeserializer<Number>() {
					@Override
					public Number deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
							throws JsonParseException {
						if (((Class) typeOfT).isAssignableFrom(LinkedTreeMap.class)) {
							return context.deserialize(json, typeOfT);
						}
						return null;
					}
				})
//				.registerTypeAdapter(String.class, new JsonDeserializer<String>() {
//					@Override
//					public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//							throws JsonParseException {
//						return null;
//					}
//				})
				.registerTypeAdapter(BigDecimal.class, new JsonDeserializer<BigDecimal>() {
			@Override
			public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return null;
			}
		});
		return GsonConverterFactory.create(asd.create());
	}

}
