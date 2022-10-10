import RNFS from 'react-native-fs';
import { Settings, Appearance } from 'react-native-readium';
import type { Locator } from 'react-native-readium';

export const EPUB_URL = `https://tcpdf.org/files/examples/example_045.pdf`;
export const EPUB_PATH = `${RNFS.DocumentDirectoryPath}/moby-dick-2.pdf`;
// export const EPUB_URL = `http://freekidsbooks.org/wp-content/uploads/2022/09/2209-All-About-Sirenia-DB-FKB.pdf`;
// export const EPUB_PATH = `${RNFS.DocumentDirectoryPath}/moby-dick.epub`;
export const INITIAL_LOCATION: Locator = {
  href: '/moby-dick-2.pdf',
  locations: {
    fragments: ['page=16'],
    position: 16,
    progression: 0.9375,
    totalProgression: 0.9375,
  },
  type: 'application/pdf',
};
// export const INITIAL_LOCATION: Locator = {
//   href: '/OPS/main3.xml',
//   title: 'Chapter 2 - The Carpet-Bag',
//   type: 'application/xhtml+xml',
//   target: 27,
//   locations: {
//     position: 24,
//     progression: 0,
//     totalProgression: 0.03392330383480826
//   },
// };

const DEFAULT_SETTINGS = new Settings();
DEFAULT_SETTINGS.appearance = Appearance.NIGHT;
export { DEFAULT_SETTINGS };
