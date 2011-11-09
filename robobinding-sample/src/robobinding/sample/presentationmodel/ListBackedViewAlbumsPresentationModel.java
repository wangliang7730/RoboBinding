/**
 * ViewAlbumsPresentationModel.java
 * 10 Oct 2011 Copyright Cheng Wei and Robert Taylor
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
package robobinding.sample.presentationmodel;

import java.util.List;

import robobinding.ItemPresentationModel;
import robobinding.internal.com_google_common.collect.Lists;
import robobinding.sample.dao.AlbumDao;
import robobinding.sample.model.Album;
import android.app.Activity;


/**
 * 
 * @since 1.0
 * @author Cheng Wei
 * @author Robert Taylor
 */
public class ListBackedViewAlbumsPresentationModel extends AbstractViewAlbumsPresentationModel
{
	public ListBackedViewAlbumsPresentationModel(Activity activity, AlbumDao albumDao)
	{
		super(activity, albumDao);
	}
	
	@ItemPresentationModel(value=AlbumItemPresentationModel.class)
	public List<Album> getAlbums()
	{
		return Lists.newArrayList(albumDao.getAll());
	}
	
}
