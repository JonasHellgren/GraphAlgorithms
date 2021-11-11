package dynamic_programming;

import dynamic_programming.calculator.BellmanCalculator;
import dynamic_programming.calculator.FindMax;
import dynamic_programming.calculator.FindMin;
import dynamic_programming.calculator.Strategy;
import dynamic_programming.models.Edge;
import dynamic_programming.models.Node;
import dynamic_programming.repo.NodeRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestDP {

    final double DISCOUNT_FACTOR=1d;
    NodeRepo nodeRepo;
    BellmanCalculator bellmanCalculator;

    @Before
    public void setup() {
        nodeRepo=new NodeRepo();

        Node node1a=new Node("1a",1);
        node1a.setEdges(Arrays.asList(new Edge("2a",22.0),new Edge("2b",8.0),new Edge("2c",12.0)));
        nodeRepo.add(node1a);

        Node node2a=new Node("2a",2);
        node2a.setEdges(Arrays.asList(new Edge("3a",8.0),new Edge("3b",10.0)));
        nodeRepo.add(node2a);

        Node node2b=new Node("2b",2);
        node2b.setEdges(Arrays.asList(new Edge("3a",25.0),new Edge("3b",13.0)));
        nodeRepo.add(node2b);

        Node node2c=new Node("2c",2);
        node2c.setEdges(Arrays.asList(new Edge("3a",12.0),new Edge("3b",13.0)));
        nodeRepo.add(node2c);

        Node node3a=new Node("3a",3);
        node3a.setEdges(Collections.emptyList() );
        nodeRepo.add(node3a);

        Node node3b=new Node("3b",3);
        node3b.setEdges(Collections.emptyList() );
        nodeRepo.add(node3b);

        bellmanCalculator=new BellmanCalculator(nodeRepo,new FindMin(),DISCOUNT_FACTOR);

    }

    @Test
    public void testSetup() {
        Assert.assertEquals(6,nodeRepo.nofItems());
        System.out.println(nodeRepo.get("1a"));
    }

    @Test
    public void testNodeRepoNodeDepth() {
        Assert.assertEquals(1,nodeRepo.findDepthMin());
        Assert.assertEquals(3,nodeRepo.findDepthMax());
    }

    @Test
    public void testDiscountFactor() {
        double df=0.5;
        BellmanCalculator bc=new BellmanCalculator(nodeRepo,new FindMin(),df);
        Assert.assertEquals(df,bc.calcDiscountFactorPowerDepth(1),0.1);
        Assert.assertEquals(df*df,bc.calcDiscountFactorPowerDepth(2),0.1);

    }

    @Test
    public void testFindBestOfEmptyList() {
        Strategy strategy=new FindMax();
        Assert.assertEquals(Strategy.BEST_IF_EMPTY_LIST,strategy.findBestInList(new ArrayList<>()),0.1);
    }

    @Test
    public void testFindBestOfSameItemsList() {
        Strategy strategy=new FindMax();
        System.out.println(strategy.findBestInList(Arrays.asList(1d,1d,1d)));
        //Assert.assertEquals(Strategy.BEST_IF_EMPTY_LIST,strategy.findBest(Arrays.asList(1d,1d,1d)),0.1);
    }

    @Test
    public void testBellmanCalculatorSetNodeValuesMinStrategy() {
        bellmanCalculator.setNodeValues();
        System.out.println(nodeRepo.toStringNodeValues());
        Assert.assertEquals(13,nodeRepo.get("2b").getValue(),0.1);
        Assert.assertEquals(8+13,nodeRepo.get("1a").getValue(),0.1);
    }

    @Test
    public void testBellmanCalculatorSetNodeValuesMinStrategyZeroDiscount() {
        double df=0.0;
        BellmanCalculator bc=new BellmanCalculator(nodeRepo,new FindMin(),df);
        bc.setNodeValues();
        System.out.println(nodeRepo.toStringNodeValues());
        Assert.assertEquals(8+0*13,nodeRepo.get("1a").getValue(),0.1);
    }

    @Test
    public void testBellmanCalculatorSetNodeValuesMaxStrategy() {
        bellmanCalculator=new BellmanCalculator(nodeRepo,new FindMax(),1);
        bellmanCalculator.setNodeValues();

        System.out.println(nodeRepo.toStringNodeValues());

        Assert.assertEquals(25,nodeRepo.get("2b").getValue(),0.1);
        Assert.assertEquals(8+25,nodeRepo.get("1a").getValue(),0.1);
    }

    @Test
    public void testBellmanCalculatorMinStrategyFindNodesOnOptimalPath() {
        bellmanCalculator.setNodeValues();
        List<Node> nodes = bellmanCalculator.findNodesOnOptimalPath(nodeRepo.get("1a"));
        List<String> filteredNames=nodes.stream().map(p->p.getName()).collect(Collectors.toList());

        System.out.println(filteredNames);
        Assert.assertEquals("1a",filteredNames.get(0));
        Assert.assertEquals("2b",filteredNames.get(1));
        Assert.assertEquals("3b",filteredNames.get(2));
    }

    @Test
    public void testBellmanCalculatorMaxStrategyFindNodesOnOptimalPath() {
        bellmanCalculator=new BellmanCalculator(nodeRepo,new FindMax(),1);
        bellmanCalculator.setNodeValues();
        List<Node> nodes = bellmanCalculator.findNodesOnOptimalPath(nodeRepo.get("1a"));
        List<String> filteredNames=nodes.stream().map(p->p.getName()).collect(Collectors.toList());

        System.out.println(filteredNames);
        Assert.assertEquals("1a",filteredNames.get(0));
        Assert.assertEquals("2b",filteredNames.get(1));
        Assert.assertEquals("3a",filteredNames.get(2));
    }


}
