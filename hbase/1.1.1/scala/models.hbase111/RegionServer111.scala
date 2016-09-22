/*
 * Copyright 2014 YMC. See LICENSE for details.
 */

package models.hbase111

import org.apache.hadoop.hbase.{ServerName, ClusterStatus}
import scala.collection.JavaConverters._
import models.hbase.RegionServer

class RegionServer111(val clusterStatus: ClusterStatus, val serverNameObj: ServerName, override val infoPort: Int) extends RegionServer {
  override val serverName = serverNameObj.getServerName
  override val hostName = serverNameObj.getHostname
  override val port = serverNameObj.getPort
  lazy val load = clusterStatus.getLoad(serverNameObj)
  override lazy val regionsLoad = load.getRegionsLoad().values.asScala.map( new RegionLoad111(_) )

  override def equals(that: Any) = {
    that match {
      case other: RegionServer111 => other.serverNameObj == serverNameObj
      case _ => false
    }
  }
}
