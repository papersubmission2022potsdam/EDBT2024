package Dema.RootNode;

import Dema.Configure.Configuration;
import Dema.Message.MessageToRoot;
import org.msgpack.MessagePack;
import org.zeromq.ZMQ;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RootSubscribeMassage implements Runnable{

    private Configuration conf;
    private ConcurrentLinkedQueue<MessageToRoot> messageToRootQueue;
    private ZMQ.Socket socketSub;
    private long counter;

    public RootSubscribeMassage(ConcurrentLinkedQueue<MessageToRoot> messageToRootQueue
            , Configuration conf, ZMQ.Socket socketSub) {
        this.messageToRootQueue =messageToRootQueue;
        this.socketSub = socketSub;
        this.conf = conf;
        this.counter = counter;
    }

    public void run() {
        MessagePack msgpack = new MessagePack();
        socketSub.subscribe("".getBytes());
        long tupleCounter = 0;
        long tupleCounterAll = 0;
        long networkOverheadR = 0;
        long networkOverheadI = 0;
        long networkOverheadL = 0;
        long begintime = System.currentTimeMillis();
        long endtime = System.currentTimeMillis();

        while (true) {
            try {
//                if(resultFromIntermedia.size() < conf.DATAGENERATORMAXIMIUMBUFFER) {
                    byte[] raw = socketSub.recv(1);
                    if(raw!=null) {
//                        MessageResult messageResult = msgpack.read(raw,
//                                MessageResult.class);
                        MessageToRoot messageToRoot = msgpack.read(raw,
                                MessageToRoot.class);
                        messageToRootQueue.offer(messageToRoot);

                        //for test
//                        networkOverheadL += System.currentTimeMillis() - raw.length;

                        if(conf.DEBUGMODE_ROOT) {
//                            if(tupleCounter == 0){
//                                tupleCounter++;
//                                tupleCounterAll+=messageResult.windowCollection.tuples.size();
//                                networkOverheadR = getNetworkOverheadR(raw.length);
//                                networkOverheadI = getNetworkOverheadI(raw.length);
//                                begintime = System.currentTimeMillis();
//                                endtime = System.currentTimeMillis();
//                                continue;
//                            }
                            tupleCounter++;
//                            tupleCounterAll+=messageResult.windowCollection.tuples.size();
//                            networkOverheadR+=getNetworkOverheadR(raw.length);
//                            networkOverheadI+=getNetworkOverheadI(raw.length);
                            if (System.currentTimeMillis() - endtime >= conf.BenchMarkOutputFrequency) {
                                endtime = System.currentTimeMillis();
                                System.out.println("rootNode--" + conf.getNodeId() + "--receiving message----"
                                        + "  nodeId:  " + messageToRoot.getNodeId()
                                        + "  Type:  " + messageToRoot.getMessageType()
//                                        + "  Throughput:  " + tupleCounter / ((endtime - begintime) / 1000.0)
//                                        + "  ThroughputTuple:  " + tupleCounterAll / ((endtime - begintime) / 1000.0)
//                                        + "  BandWidth(Root):  " + networkOverheadR  / ((endtime - begintime) / 1000.0)
//                                        + "  BandWidth(Inter):  " + networkOverheadI  / ((endtime - begintime) / 1000.0)
//                                        + "  Allcounter:  " + tupleCounter
//                                        + "  AllcounterTuple:  " + tupleCounterAll
//                                        + "  counter:  " + (tupleCounterAll - counter)
//                                        + "  NetworkOverhead(Root):  " + networkOverheadR
//                                        + "  NetworkOverhead(Inter):  " + networkOverheadI
//                                        + "  nodeId:  " + tuple.EVENT / 10
//                                        + "  result:  " + (System.currentTimeMillis() - tuple.DATA)
//                                        + "  time:  " + tuple.TIME
//                                        + "  event:  " + tuple.EVENT
//                                        + "  network cost:  " + networkOverheadL
//                                        + "  Time:  " + (endtime - begintime) / 1000.0
//                                        + "  GCTime:  " + getGarbageCollectionTime()
//                                        + "  GC/Time-Ratio:  " + (double) getGarbageCollectionTime() / (endtime - begintime)
//                                        + "  Queue:  " + resultFromIntermedia.size()
                                );
                                counter = tupleCounterAll;
                            }
                        }
                    }
//                }else {
//                    System.out.println("WARNING!!!!:  " + resultFromIntermedia.size());
//                    Thread.sleep(conf.DATAGENERATORFREQUENCY);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static long getGarbageCollectionTime() {
        long collectionTime = 0;
        for (GarbageCollectorMXBean garbageCollectorMXBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            collectionTime += garbageCollectorMXBean.getCollectionTime();
        }
        return collectionTime;
    }
    private static long getNetworkOverheadR(int rawSize) {
        return (rawSize / (9000 - 46) + 1) * (44) + (44 + 44);
    }
    private static long getNetworkOverheadI(int rawSize) {
        return (rawSize / (9000 - 46) + 1) * (46) + (45 + 45) + rawSize;
    }
}
