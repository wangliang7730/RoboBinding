/**
 * Copyright 2011 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.robobinding.viewattribute;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.ParameterizedType;

import org.junit.Before;
import org.robobinding.presentationmodel.PresentationModelAdapter;

import android.app.Activity;
import android.view.View;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class AbstractCommandViewAttributeTest<ViewType extends View, CommandViewAttributeType extends AbstractCommandViewAttribute<? super ViewType>>
{
	private final String commandName = "someCommand";

	private MockFunction mockFunction;
	private PresentationModelAdapter mockPresentationModelAdapter;
	
	protected ViewType view;
	private CommandViewAttributeType commandViewAttribute;
	
	@Before
	public void setUp()
	{
		mockFunction = new MockFunction();
		
		mockPresentationModelAdapter = mock(PresentationModelAdapter.class);
		when(mockPresentationModelAdapter.findFunction(eq(commandName), (Class<?>)any())).thenReturn(mockFunction);
		
		createViewAndAttribute();
		initializeAttribute();
	}
	
	private void createViewAndAttribute()
	{
		ParameterizedType superclass = (ParameterizedType)getClass().getGenericSuperclass();
		
        view = ParameterizedTypeUtils.createTypeArgument(superclass, 0);
        commandViewAttribute = ParameterizedTypeUtils.createTypeArgument(superclass, 1);
	}

	private void initializeAttribute()
	{
		createViewAndAttribute();
		
		commandViewAttribute.setView(view);
		commandViewAttribute.setCommandName(commandName);
	}
	
	protected void bindAttribute()
	{
		commandViewAttribute.bind(mockPresentationModelAdapter, new Activity());
	}
	
	protected void assertEventReceived(Class<?> eventClass)
	{
		assertTrue(mockFunction.commandInvoked);
		assertThat(mockFunction.argsPassedToInvoke[0], instanceOf(eventClass));
	}
}
