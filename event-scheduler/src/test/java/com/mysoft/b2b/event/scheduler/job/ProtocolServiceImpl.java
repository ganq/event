package com.mysoft.b2b.event.scheduler.job;

import java.rmi.RemoteException;
import java.util.List;

import com.mysoft.b2b.event.api.ProtocolService;
import com.mysoft.b2b.event.api.event.Event;

public class ProtocolServiceImpl implements ProtocolService {

	protected ProtocolServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dealEvent(Event event) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("=============得到事件=========>"+event.getEventContent());
		return true;
	}

	@Override
	public boolean dealEvent(List<Event> eventList) throws RemoteException{
		// TODO Auto-generated method stub
		return false;
	}

}
