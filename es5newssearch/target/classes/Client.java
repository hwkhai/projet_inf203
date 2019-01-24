
    public static final String CLUSTER_NAME = "elasticsearch_weiliu";  //集群名称
    public static final String HOST_IP = "localhost";    //IP
    public static final int TCP_PORT = 9300;  //TCP端口
    static Settings settings = Settings.builder()
            .put("cluster.name", CLUSTER_NAME)
            .build();
    TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(
            InetAddress.getByName(HOST_IP), TCP_PORT));