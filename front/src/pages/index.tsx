import { useContext, FormEvent, useState } from 'react'
import Head from 'next/head'
import logoImg from '../../public/logoauto.png'
import styles from '@/styles/home.module.scss'

import Image from 'next/image'
import { Input } from '@/components/ui/Input'
import { Button } from '@/components/ui/Button'
import { AuthContext } from '@/contexts/AuthContext'
import Link from 'next/link'
import { canSSRGuest } from '../utils/canSSRGuest'
export default function Home() {
  const { signIn } = useContext(AuthContext)

  const [email, setEmail] = useState('')
  const [senha, setSenha] = useState('');

  const [loading, setLoading] = useState(false);

  async function handleLogin(event: FormEvent){
    event.preventDefault();

    if(email === '' || senha === ''){
      alert("PREENCHA OS DADOS")
      return;
    }

    setLoading(true);

    let data = {
      email,
      senha
    }

    await signIn(data)

    setLoading(false);
  }

  return (
   
    < >
      <Head>
        <title>Login Auto Center</title>
      </Head>
    <div className={styles.containerCenter}>
      <Image src={logoImg} alt="Logo Auto Center"/>
      <div className={styles.login}>
      <h1>Auto Center</h1>


      <form onSubmit={handleLogin}>
        <Input
          placeholder="Digite seu email"
          type='text'
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
         <Input
          placeholder="Digite sua senha"
          type='password'
          value={senha}
          onChange={(e) => setSenha(e.target.value)}

        />

        <Button type="submit"
        loading={loading}>

          Acessar
        </Button>
      </form>
      <Link href="/cadastros" passHref>
        <div className={styles.text}>
         Nao possui uma conta? Cadastre-se!
        </div>
      </Link>
      </div>
    </div>
    </>
  )
}

export const getServerSideProps = canSSRGuest(async(context) => {

  return{
    props: {}
  }
})