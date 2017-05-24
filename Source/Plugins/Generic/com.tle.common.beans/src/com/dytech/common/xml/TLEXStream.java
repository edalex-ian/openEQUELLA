/*
 * Created on Jun 9, 2005
 */
package com.dytech.common.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import com.dytech.devlib.PropBagEx;
import com.thoughtworks.xstream.MarshallingStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.core.DefaultConverterLookup;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomReader;
import com.thoughtworks.xstream.io.xml.DomWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 *
 */
public class TLEXStream extends XStream
{
	private XppDriver xppDriver;
	private MarshallingStrategy marshallingStrategy;

	public TLEXStream()
	{
		xppDriver = new XppDriver();
		registerConverter(new XMLDataConverter());
	}

	protected HierarchicalStreamDriver getDefaultDriver()
	{
		return xppDriver;
	}

	public Object fromXML(String string, Class clazz)
	{
		return fromXML(new StringReader(string), clazz);
	}

	@Override
	public Object fromXML(String string, Object object)
	{
		return fromXML(new StringReader(string), object);
	}

	public Object fromXML(Reader reader, Class clazz)
	{
		return fromXML(getDefaultDriver(), reader, null, clazz);
	}

	@Override
	public Object fromXML(Reader string, Object object)
	{
		return fromXML(getDefaultDriver(), string, object, object.getClass());
	}

	public Object fromXML(PropBagEx xml, Class clazz)
	{
		return fromXML(new DomReader(xml.getRootElement()), null, clazz);
	}

	public Object fromXML(PropBagEx xml, Object object)
	{
		return fromXML(new DomReader(xml.getRootElement()), object, object.getClass());
	}

	private Object fromXML(HierarchicalStreamDriver driver, Reader reader, Object object, final Class clazz)
	{
		HierarchicalStreamReader hreader = driver.createReader(reader);
		return fromXML(hreader, object, clazz);
	}

	private Object fromXML(HierarchicalStreamReader hreader, Object object, final Class clazz)
	{
		Mapper mapper = super.getMapper();
		mapper = new MapperWrapper(mapper)
		{
			@Override
			public Class realClass(String elementName)
			{
				return clazz;
			}
		};

		return marshallingStrategy.unmarshal(object, hreader, null, getDefaultConverterLookup(), mapper);
	}

	public PropBagEx toPropBag(Object object, String rootNodeName)
	{
		PropBagEx xml = new PropBagEx();
		HierarchicalStreamWriter writer = new DomWriter(xml.getRootElement());
		toXML(writer, object, rootNodeName);
		return xml.getSubtree(rootNodeName);
	}

	public String toXML(Object object, String rootNodeName) throws IOException
	{
		return toXML(new StringWriter(), object, rootNodeName);
	}

	public String toXML(Writer writer, Object object, String rootNodeName) throws IOException
	{
		HierarchicalStreamWriter hwriter = getDefaultDriver().createWriter(writer);
		toXML(hwriter, object, rootNodeName);

		writer.flush();
		writer.close();

		return writer.toString();
	}

	private void toXML(HierarchicalStreamWriter hwriter, Object object, final String rootNodeName)
	{
		Mapper mapper = super.getMapper();
		mapper = new MapperWrapper(mapper)
		{
			@Override
			public String serializedClass(Class type)
			{
				return rootNodeName;
			}
		};

		marshallingStrategy.marshal(hwriter, object, getDefaultConverterLookup(), mapper, null);

	}

	private ConverterLookup getDefaultConverterLookup()
	{
		return getConverterLookup();
	}

	@Override
	public void setMarshallingStrategy(MarshallingStrategy marshallingStrategy)
	{
		this.marshallingStrategy = marshallingStrategy;
		super.setMarshallingStrategy(marshallingStrategy);
	}
}
