import * as Styles from './styles.js'
import Image from 'next/image'
import logo from '../../../../public/assets/logo.png'
export default function Header() {
  return (
    <Styles.Container>
      <Styles.Logo>
        <a href="/">
          <Image src={logo} width={250} height={90} />
        </a>
      </Styles.Logo>
      {/* <Styles.Avatar>
        <p>D</p>
      </Styles.Avatar> */}
    </Styles.Container>
  )
}
