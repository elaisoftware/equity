package com.cienet.equity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Transcation processeor
 * @author JiangLai
 * @since 2020.5.20
 */
public class TransactionProcessor {
	Map<String, Integer> positionMap = new HashMap<>();
	HashSet<Integer> cancelSet = new HashSet<>();
	Map<Integer, Transaction> tradeIdMap = new HashMap<>();
	Map<Integer, Transaction> updateMap = new HashMap<>();

	public void receive(Transaction tran) {
		save(tran);
		process(tran);
	}

	public void save(Transaction tran) {
	}

	protected void process(Transaction tran) {
		if (cancelSet.contains(tran.getTradeID())) {
			return;
		}
		if (tran.getAction().equals(Actions.INSERT)) {
			processInsert(tran);
		} else if (tran.getAction().equals(Actions.CANCEL)) {
			processCancel(tran);
		} else if (tran.getAction().equals(Actions.UPDATE)) {
			processUpdate(tran);
		}

	}

	private void processInsert(Transaction tran) {
		if (updateMap.get(tran.getTradeID()) != null
				&& updateMap.get(tran.getTradeID()).getVersion() > tran.getVersion()) {	
			return;
		}
		int quantity = 0;
		if (positionMap.get(tran.getSecurityCode()) != null) {
			quantity = positionMap.get(tran.getSecurityCode());
		}
		if (tran.getBuySell().equals(BuySell.BUY)) {
			quantity += tran.getQuatity();
		} else {
			quantity -= tran.getQuatity();
		}
		positionMap.put(tran.getSecurityCode(), quantity);
		
		Transaction transByTradeId = tradeIdMap.get(tran.getTradeID());
		if(transByTradeId == null) {
			Transaction anTran = new Transaction();
			anTran.setTradeID(tran.getTradeID());
			anTran.setSecurityCode(tran.getSecurityCode());
			if (tran.getBuySell().equals(BuySell.BUY)) {
				anTran.setQuatity(tran.getQuatity());
			} else {
				anTran.setQuatity(tran.getQuatity() * -1);
			}
			tradeIdMap.put(anTran.getTradeID(), anTran);
		}else {
			int anQuantity = transByTradeId.getQuatity();
			if (tran.getBuySell().equals(BuySell.BUY)) {
				anQuantity += tran.getQuatity();
			} else {
				anQuantity -= tran.getQuatity();
			}
			transByTradeId.setQuatity(anQuantity);
		}
	}

	private void processCancel(Transaction tran) {
		cancelSet.add(tran.getTradeID());
		Transaction transByTradeId = tradeIdMap.get(tran.getTradeID());
		if (transByTradeId != null) {
			if (positionMap.get(transByTradeId.getSecurityCode()) != null) {
				int quantity = positionMap.get(transByTradeId.getSecurityCode());
				quantity = -1 * transByTradeId.getQuatity() + quantity;
				positionMap.put(transByTradeId.getSecurityCode(), quantity);
			}
		}
	}

	private void processUpdate(Transaction tran) {
		if (updateMap.get(tran.getTradeID()) != null
				&& updateMap.get(tran.getTradeID()).getVersion() > tran.getVersion()) {
			return;
		}
		Transaction upTran = new Transaction();
		upTran.setTradeID(tran.getTradeID());
		upTran.setSecurityCode(tran.getSecurityCode());
		upTran.setQuatity(tran.getQuatity());
		updateMap.put(upTran.getTradeID(), upTran);
			
		Transaction transByTradeId = tradeIdMap.get(tran.getTradeID());
		if (transByTradeId != null) {
			if (positionMap.get(transByTradeId.getSecurityCode()) != null) {
				int quantity = positionMap.get(transByTradeId.getSecurityCode());
				quantity = -1 * transByTradeId.getQuatity() + quantity;
				positionMap.put(transByTradeId.getSecurityCode(), quantity);
				positionMap.put(tran.getSecurityCode(), tran.getQuatity());
			}
		}
		
		Transaction anTran = new Transaction();
		anTran.setTradeID(tran.getTradeID());
		anTran.setSecurityCode(tran.getSecurityCode());
		anTran.setQuatity(tran.getQuatity());
		tradeIdMap.put(anTran.getTradeID(), anTran);
	}

	public Map<String,Integer> getPosition() {
		return positionMap;
	}
}
