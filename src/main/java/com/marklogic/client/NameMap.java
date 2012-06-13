/*
 * Copyright 2012 MarkLogic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marklogic.client;

import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

/**
 * A NameMap defines the interface for maps where the key is
 * a QName (a name qualified by a URI for global uniqueness or
 * for association with a domain).
 * @param <V>
 */
public interface NameMap<V> extends Map<QName,V> {
	public NamespaceContext getNamespaceContext();
	public void setNamespaceContext(NamespaceContext context);

	public boolean containsKey(String name);

	public V get(String name);
	public <T> T get(QName name, Class<T> as);
	public <T> T get(String name, Class<T> as);

	public V put(String name, V value);

	public V remove(String name);
}
