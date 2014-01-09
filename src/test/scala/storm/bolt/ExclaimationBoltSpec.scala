package scalastormtwitter.bolt

import org.scalatest._

import backtype.storm.Config
import backtype.storm.topology.BasicOutputCollector
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.tuple.{ Fields, Tuple, Values ;
import org.testing.annotations.{ DataProvider, Test}
import scalastormtwitter.tools.MockTupleHelpers;

import com.google.common.collect.Lists;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class ExclamationBoltSpec extends Specification {
	"The 'exclaim' method from Exclaimer passed a string" should {
		"result in the original string  appended with '!!!' " in {
			val bolt = new ExclaimationBolt()
			val original = "foo"
			val result = ExclaimationBolt("foo")
			result must beEqualTo(original + "!!!")
		}
	}
}
