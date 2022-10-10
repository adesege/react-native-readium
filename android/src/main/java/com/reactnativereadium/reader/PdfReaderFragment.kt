/*
 * Copyright 2021 Readium Foundation. All rights reserved.
 * Use of this source code is governed by the BSD-style license
 * available in the top-level LICENSE file of the project.
 */

package com.reactnativereadium.reader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commitNow
import androidx.lifecycle.ViewModelProvider
import com.facebook.react.util.RNLog
import com.reactnativereadium.R
import org.readium.r2.navigator.Navigator
import org.readium.r2.navigator.pdf.PdfNavigatorFragment
import org.readium.r2.shared.fetcher.Resource
import org.readium.r2.shared.publication.Link
import org.readium.r2.shared.publication.Locator
import org.readium.r2.shared.publication.Publication

class PdfReaderFragment : VisualReaderFragment(), PdfNavigatorFragment.Listener {

  override lateinit var model: ReaderViewModel
  override lateinit var navigator: Navigator
  private lateinit var publication: Publication
  private lateinit var factory: ReaderViewModel.Factory

  fun initFactory(
    publication: Publication,
    initialLocation: Locator?
  ) {
    factory = ReaderViewModel.Factory(
      publication,
      initialLocation
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    ViewModelProvider(this, factory)
      .get(ReaderViewModel::class.java)
      .let {
        model = it
        publication = it.publication
      }

    childFragmentManager.fragmentFactory =
      PdfNavigatorFragment.createFactory(publication, model.initialLocation, this)

    setHasOptionsMenu(true)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = super.onCreateView(inflater, container, savedInstanceState)
    if (savedInstanceState == null) {
      childFragmentManager.commitNow {
        add(
          R.id.fragment_reader_container,
          PdfNavigatorFragment::class.java,
          Bundle(),
          NAVIGATOR_FRAGMENT_TAG
        )
      }
    }
    navigator = childFragmentManager.findFragmentByTag(NAVIGATOR_FRAGMENT_TAG)!! as Navigator

    return view
  }

  override fun onResourceLoadFailed(link: Link, error: Resource.Exception) {
    val message = when (error) {
      is Resource.Exception.OutOfMemory -> "The PDF is too large to be rendered on this device"
      else -> "Failed to render this PDF"
    }

    RNLog.e(message)
  }

  companion object {
    const val NAVIGATOR_FRAGMENT_TAG = "navigator"

    fun newInstance(): PdfReaderFragment {
      return PdfReaderFragment()
    }
  }
}
