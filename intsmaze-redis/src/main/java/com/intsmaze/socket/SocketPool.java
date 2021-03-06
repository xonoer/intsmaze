//package cn.intsmaze.socket;
//
//import java.net.Socket;
//import java.net.URI;
//
//import org.apache.commons.pool2.impl.GenericObjectPool;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//
//import redis.clients.jedis.Jedis;
//
//
//
//public class SocketPool extends Pool<Socket>{
//
//	 public SocketPool() {
//		    this(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
//		  }
//
//		  public SocketPool(final GenericObjectPoolConfig poolConfig, final String host) {
//		    this(poolConfig, host, Protocol.DEFAULT_PORT, Protocol.DEFAULT_TIMEOUT, null,
//		        Protocol.DEFAULT_DATABASE, null);
//		  }
//
//		  public SocketPool(String host, int port) {
//		    this(new GenericObjectPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT, null,
//		        Protocol.DEFAULT_DATABASE, null);
//		  }
//
//		  public SocketPool(final String host) {
//		    URI uri = URI.create(host);
//		    if (JedisURIHelper.isValid(uri)) {
//		      String h = uri.getHost();
//		      int port = uri.getPort();
//		      String password = JedisURIHelper.getPassword(uri);
//		      int database = JedisURIHelper.getDBIndex(uri);
//		      this.internalPool = new GenericObjectPool<Jedis>(new JedisFactory(h, port,
//		          Protocol.DEFAULT_TIMEOUT, password, database, null), new GenericObjectPoolConfig());
//		    } else {
//		      this.internalPool = new GenericObjectPool<Jedis>(new JedisFactory(host,
//		          Protocol.DEFAULT_PORT, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, null),
//		          new GenericObjectPoolConfig());
//		    }
//		  }
//
//		  public SocketPool(final URI uri) {
//		    this(new GenericObjectPoolConfig(), uri, Protocol.DEFAULT_TIMEOUT);
//		  }
//
//		  public SocketPool(final URI uri, final int timeout) {
//		    this(new GenericObjectPoolConfig(), uri, timeout);
//		  }
//
//		  public SocketPool(final GenericObjectPoolConfig poolConfig, final String host, int port,
//		      int timeout, final String password) {
//		    this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE, null);
//		  }
//
//		  public SocketPool(final GenericObjectPoolConfig poolConfig, final String host, final int port) {
//		    this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, null);
//		  }
//
//		  public SocketPool(final GenericObjectPoolConfig poolConfig, final String host, final int port,
//		      final int timeout) {
//		    this(poolConfig, host, port, timeout, null, Protocol.DEFAULT_DATABASE, null);
//		  }
//
//		  public SocketPool(final GenericObjectPoolConfig poolConfig, final String host, int port,
//		      int timeout, final String password, final int database) {
//		    this(poolConfig, host, port, timeout, password, database, null);
//		  }
//
//		  public SocketPool(final GenericObjectPoolConfig poolConfig, final String host, int port,
//		      int timeout, final String password, final int database, final String clientName) {
//		    super(poolConfig, new JedisFactory(host, port, timeout, password, database, clientName));
//		  }
//
//		  public SocketPool(final GenericObjectPoolConfig poolConfig, final URI uri) {
//		    this(poolConfig, uri, Protocol.DEFAULT_TIMEOUT);
//		  }
//
//		  public SocketPool(final GenericObjectPoolConfig poolConfig, final URI uri, final int timeout) {
//		    super(poolConfig, new JedisFactory(uri, timeout, null));
//		  }
//
//		  @Override
//		  public Socket getResource() {
//		    Jedis jedis = super.getResource();
//		    jedis.setDataSource(this);
//		    return jedis;
//		  }
//
//		  /**
//		   * @deprecated starting from Jedis 3.0 this method won't exist. Resouce cleanup should be done
//		   *             using @see {@link redis.clients.jedis.Jedis#close()}
//		   */
//		  @Deprecated
//		  public void returnBrokenResource(final Jedis resource) {
//		    if (resource != null) {
//		      returnBrokenResourceObject(resource);
//		    }
//		  }
//
//		  /**
//		   * @deprecated starting from Jedis 3.0 this method won't exist. Resouce cleanup should be done
//		   *             using @see {@link redis.clients.jedis.Jedis#close()}
//		   */
//		  @Deprecated
//		  public void returnResource(final Jedis resource) {
//		    if (resource != null) {
//		      try {
//		        resource.resetState();
//		        returnResourceObject(resource);
//		      } catch (Exception e) {
//		        returnBrokenResource(resource);
//		        throw new JedisException("Could not return the resource to the pool", e);
//		      }
//		    }
//		  }
//}
