

	import static org.elasticsearch.common.xcontent.XContentFactory.*;

	XContentBuilder builder = jsonBuilder()
	    .startObject()
	        .field("user", "WeiLIU")
	        .field("postDate", "2019-12-30")
	        .field("message", "trying out Elasticsearch")
	    .endObject()

	 String json = builder.string();

