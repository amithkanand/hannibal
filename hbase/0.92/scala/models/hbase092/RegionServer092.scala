/*
 * Copyright 2013 Sentric. See LICENSE for details.
 */

package models.hbase092

import org.apache.hadoop.hbase.HServerLoad.RegionLoad
import org.apache.hadoop.hbase.{ServerName, ClusterStatus, HServerInfo}
import scala.collection.JavaConversions._
import models.hbase.RegionServer

class RegionServer092(val clusterStatus: ClusterStatus, val serverNameObj: ServerName) extends RegionServer {
  override val serverName = serverNameObj.getServerName
  override val hostName = serverNameObj.getHostname
  override val port = serverNameObj.getPort
  override val infoPort = 60030
  override lazy val load = clusterStatus.getLoad(serverNameObj)
  override lazy val regionsLoad = load.getRegionsLoad().values.toIterable

  override def equals(that: Any) = {
    that match {
      case other: RegionServer092 => other.serverNameObj == serverNameObj
      case _ => false
    }
  }
}
