package storm.topology

import backtype.storm.{ Config, LocalCluster, StormSubmitter }
import backtype.storm.tuple.{ Fields, Tuple, Values}
import backtype.storm.testing.TestWordSpout
import backtype.storm.topology.TopologyBuilder
import backtype.storm.utils.Utils

//import backtype.storm.task.OutputCollector;
//import backtype.storm.task.TopologyContext;
//import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.IRichSpout
import backtype.storm.topology.base.BaseRichBolt
//import backtype.storm.tuple.Fields;
//import backtype.storm.tuple.Tuple;
//import backtype.storm.tuple.Values;
//import backtype.storm.utils.Utils;

object ExclaimationTopology {
  def main(args: Array[String]) {  
		import storm.bolt.ExclaimationBolt

		val builder: TopologyBuilder = new TopologyBuilder()

		builder.setSpout("word", new TestWordSpout(), 10)
		builder.setBolt("exclaim", new ExclaimationBolt(), 3).shuffleGrouping("word")

		val config = new Config()
		config.setDebug(true)

		if(args != null && args.length > 0){
		  config.setNumWorkers(3)
			StormSubmitter.submitTopology(args(0), config, builder.createTopology())
		} else {
			val cluster: LocalCluster = new LocalCluster()
			cluster.submitTopology("ExclaimationTopology", config, builder.createTopology())
			Utils.sleep(5000)
			cluster.killTopology("ExclaimationTopology")
			cluster.shutdown()
		}
	}
}
