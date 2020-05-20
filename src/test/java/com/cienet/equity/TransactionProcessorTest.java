package com.cienet.equity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransactionProcessorTest {
	@Test
	public void testTransaction() {
		Transaction tran1 = new Transaction();
		tran1.setTranscationID(1);
		tran1.setTradeID(1);
		tran1.setVersion(1);
		tran1.setSecurityCode("REL");
		tran1.setQuatity(50);
		tran1.setAction(Actions.INSERT);
		tran1.setBuySell(BuySell.BUY);
		TransactionProcessor processor = new TransactionProcessor();
		processor.receive(tran1);
		int result = processor.getPosition().get("REL");
		assertEquals(50,result);
	}
}
